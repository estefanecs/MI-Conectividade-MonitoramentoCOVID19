# -*- coding: utf-8 -*-
"""
 * Componente Curricular: MI Concorrência e Conectividade
 * Autor: Messias Júnior Lira da Silva
 *
 * Declaro que este código foi elaborado por mim de forma individual e
 * não contém nenhum trecho de código de outro colega ou de outro autor,
 * tais como provindos de livros e apostilas, e páginas ou documentos
 * eletrônicos da Internet. Qualquer trecho de código de outra autoria que
 * uma citação para o  não a minha está destacado com  autor e a fonte do
 * código, e estou ciente que estes trechos não serão considerados para fins
 * de avaliação. Alguns trechos do código podem coincidir com de outros
 * colegas pois estes foram discutidos em sessões tutorias.
"""
import os
import sys
import json
import time
import socket
import mercury
import threading
from collections import deque
from datetime import datetime

HOST=''
PORT=5023
fila = deque()
rodando = False
tempoMinimo = 0
tempoInicial = 0
tempoQualificacao = 0
quantidadeVoltas = 0
leituraLiberada = False
leituraValidaLiberada = False
ultimas_voltas_dadas = []
fila_leituras_validas = deque()
threadCondition = threading.Condition()
reader = None
cliente = None
threads_on = False


"""
Thread que realiza leituras sucessivas no sensor e armazena os dados lidos em uma fila
"""
def produz():
    global threadCondition, leituraLiberada, rodando, tempoInicial
    while True:
        threadCondition.acquire()
            
        while leituraLiberada:
            try:
                threadCondition.wait()
            except Exception as e:
                return e
        if(rodando):
            reader.start_reading(lambda tag: guarda_leitura(tag.epc, tag.rssi, tag.timestamp))# datetime.fromtimestamp(tag.timestamp)
            time.sleep(0.1)
            reader.stop_reading()
        
        leituraLiberada = True
        threadCondition.notify()
        threadCondition.release()

"""
Método guarda as leituras
"""       
def guarda_leitura(epc, rssi, tempo):
    fila.append({"ACAO":"PUT::atualizar/corrrida", "TAG":str(epc), "FREQ":rssi, "TEMPO":tempo, "TEMPO_S": ""})

"""
Thread que trata as leituras, verifica se o tempo de leitura foi maior que o temopo minimo da volta
"""
def trata():
    global rodando, threadCondition, leituraLiberada, leituraValidaLiberada
    while True:
        threadCondition.acquire()
        while not(leituraLiberada) and leituraValidaLiberada:
            try:
                threadCondition.wait()
            except Exception as e:
                return e
           
        if rodando and len(fila)>0:
            item = fila.popleft()
            if deu_volta(item):
                fila_leituras_validas.append(item)
                ultimas_voltas_dadas.append(item)
                    
        leituraLiberada = False
        leituraValidaLiberada = True
        threadCondition.notify()
        threadCondition.release()


"""
Metodo que verifica se o carro deu uma volta
"""
def deu_volta(dado):
    
    global tempoMinimo, tempoInicial
    tag = dado["TAG"]
    tempo = dado["TEMPO"]
    jaDeuVolta=False
    minutesMin, secondsMin = divmod(tempoMinimo, 60)
    diff = tempo - tempoInicial
    hours, r = divmod(diff, 3600)
    minutes, seconds = divmod(r, 60)   
    dado["TEMPO_S"]="{minutes:02.0f}:{seconds:08.5f}".format(minutes=minutes, seconds=seconds)
    
    if len(ultimas_voltas_dadas)==0:
        dado["TEMPO_S"]="{minutes:02.0f}:{seconds:08.5f}".format(minutes=minutes, seconds=seconds)
        #print(dado["TEMPO_S"])
        return compara(minutes, seconds)

    elif len(ultimas_voltas_dadas)>0:
        for item in ultimas_voltas_dadas:
            if item["TAG"] == tag:
                jaDeuVolta=True
                carro = item
                break
        
        if(jaDeuVolta):
            diff = tempo - carro["TEMPO"]
            hours, r = divmod(diff, 3600)
            minutes, seconds = divmod(r, 60)

            #t="{minutes:02.0f}:{seconds:06.3f}".format(minutes=minutes, seconds=seconds)
            if compara(minutes, seconds):
                dado["TEMPO_S"]="{minutes:02.0f}:{seconds:06.3f}".format(minutes=minutes, seconds=seconds)
                ultimas_voltas_dadas.remove(carro)
                return True
        else:
            dado["TEMPO_S"]="{minutes:02.0f}:{seconds:06.3f}".format(minutes=minutes, seconds=seconds)
            return compara(minutes, seconds)
    



def compara(minutes, seconds):
    minutesMin, secondsMin = divmod(tempoMinimo, 60)
    if(minutes == minutesMin):
        if(seconds == secondsMin):
            return False
        elif(seconds > secondsMin):
            return True
        else:
            return False
    elif(minutes > minutesMin):
        return True
    else:
        return False

    
"""
Thread que envia as leituras validas (carros que deram uma volta) para o cliente
"""
def consome():
    global threadCondition, leituraValidaLiberada, cliente
    while True:
        threadCondition.acquire()
        while not(leituraValidaLiberada):
            try:
                threadCondition.wait()
            except Exception as e:
                return e

        if len(fila_leituras_validas)>0 and rodando:
            dado = fila_leituras_validas.popleft()
            print("Enviar:", dado)
            objeto = json.dumps(dado)
            cliente.sendall(bytes((objeto+"\n").encode('utf8')))
        leituraVaidaLiberada = False
        threadCondition.notify()
        threadCondition.release()

"""
Metodo que altera o valor rodando para false, o que bloqueia a acao das threads, 
finalizando assim a corrida ou qualificacaoo
"""
def finaliza():
    global rodando
    rodando= False
    print("Finalizou")

"""
Metodo que altera a variavel rodando para true, liberando a acao das threads que ja foram iniciadas,
dando inicio a corrida
"""
def corrida():
    print("\n\n **************** CORRIDA ****************\n\n")
    global rodando, tempoInicial, leituraLiberada, leituraValidaLiberada
    tempoInicial = time.time()
    leituraLiberada=False
    leituraValidaLiberada=False
    rodando = True
    

"""
Metodo que inicia as threads e inicia a qualificacao
"""
def qualificacao(conteudo, produtor, consumidor, tratador):
    print("\n\n **************** QUALIFICACAO ****************\n\n")
    global rodando, cliente, tempoInicial, tempoMinimo, qtdPilotos, threads_on
    
    dados = conteudo.split(",")
    tempo = dados[0]
    qtd = dados[1]
    tempoMinimo = int(tempo)
    qtdPilotos = int(qtd)
    tempoInicial = time.time()
    
    if threads_on:#se as threads j� foram iniciadas, apenas desbloqueia
        leituraLiberada=False
        leituraValidaLiberada=False
        rodando = True
        
    else : #inicia as threads 
        threads_on = True
        produtor.start()
        tratador.start()   
        consumidor.start()
        rodando = True

"""
Metodo que envia a tag lida pelo sensor para o cliente
"""
def enviarTag(cliente):
    global reader

    dado={"ACAO":"PUT::configurar/carro", "TAG":str(reader.read()[0])}
    objeto = json.dumps(dado)
    cliente.sendall(bytes(objeto.encode('utf8')))
    print("Enviado")

"""
Método que encerra a execução quando solicitado pelo cliente
"""
def encerrar():
    os._exit(0)

"""
Método que configura o leitor com os dados recebidos pelo cliente
"""
def configurarLeitor(conteudo):
    global reader
    
    dados = json.loads(conteudo)
    print ("Baud:", dados["baud"])
    print ("Regiao: " + dados["regiao"])
    print ("Porta serial:" + dados["porta_serial"])
    print ("Potencia: ", dados["potencia"])
    
    # configura a leitura na porta serial onde esta o sensor
    reader = mercury.Reader(dados["porta_serial"], baudrate=int(dados["baud"]))#tmr:///dev/ttyUSB0 baudrate=230400
    # para funcionar use sempre a regiao "NA2" (Americas)
    reader.set_region(dados["regiao"])#NA2
    # nao altere a potencia do sinal para nao prejudicar a placa
    reader.set_read_plan([1], "GEN2", read_power=int(dados["potencia"]))#1100
    # realiza a leitura das TAGs proximas e imprime na tela
    print ("Configurou")


"""
Método que chama os métodos POST de acordo com tipo de ação que o cliente deseja executar.
"""
def post(conteudo):
    acao = conteudo[1]
    if(acao == "configurar/leitor"):
        configurarLeitor(conteudo[2])
        
    elif(acao == "finalizar/leituras"):
        finaliza()
        
    elif(acao =="encerrar"):
        print("Encerrando...")
        encerrar()

"""
Método que chama os métodos GET de acordo com tipo de ação que o cliente deseja executar.
"""
def get(cliente, conteudo, produtor, consumidor, tratador):
    acao = conteudo[1]
    if(acao == "configurar/carro"):
        enviarTag(cliente)
        
    elif(acao == "qualificacao/inicio"):
        global fila, reader
        qualificacao(conteudo[2], produtor, consumidor, tratador)
    
    elif(acao == "corrida/inicio"):
        corrida()


##### INICIO #####
produtor = threading.Thread(target=produz)
consumidor = threading.Thread(target=consome)
tratador = threading.Thread(target=trata)

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((HOST,PORT))
s.listen(5) #espera pelo cliente

while True:
    print ('Ouvindo...', PORT)
    c, addr = s.accept()
    print ('Conectado: ', addr)
    
    recebido = c.recv(4096).decode('utf-8')
    conteudo = recebido.split('::')
    cliente =c

    if(conteudo[0] == "GET"):
        get(c, conteudo, produtor, consumidor, tratador)

    elif(conteudo[0] == "POST"):
        post(conteudo)
        
    if not(rodando):
        c.close()
        