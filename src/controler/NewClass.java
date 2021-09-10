package controler;


import model.Servidor;
import java.io.IOException;
import org.json.JSONException;

/**
 *
 * @author casa
 */
public class NewClass {
    public static void main(String[] args) throws IOException, JSONException, ClassNotFoundException {
        
        Cliente comunicador = Cliente.getInstancia();
        comunicador.getDados("GET/cadastrarPaciente");
    }
}
