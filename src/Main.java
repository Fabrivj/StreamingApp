import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import org.search.*;

public class Main {
    public static void main(String[] args) {
        StreamingServiceManager manager = StreamingServiceManager.getInstancia();
        String accessToken = "5ba21cbaa50cab330f1b4fc6a3d1b61e"; // Asegúrate de usar el token correcto
        manager.setServicio(new VimeoService(accessToken)); // Pasar el token correctamente

        String query = "Frozen";
        Collection<SearchResult> resultados = manager.consultarServicio(query, new Vector<>());

        if (resultados != null && !resultados.isEmpty()) {
            for (SearchResult resultado : resultados) {
                System.out.println(resultado);
            }
        } else {
            System.out.println("No se encontraron resultados para la búsqueda: " + query);
        }
    }
}