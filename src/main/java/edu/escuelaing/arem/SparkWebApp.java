package edu.escuelaing.arem;

import spark.Request;
import spark.Response;
import static spark.Spark.*;

/**
 * Propiedades de la clase SparkWebApp
 */
public class SparkWebApp {

    /**
     * Método principal de la clase. Define las peticiones Http que se realizan
     *
     * @param args de tipo String[]
     */
    public static void main(String[] args) {
        port(getPort());
        get("/submit", (req, res) -> getData(req, res));
        get("/", (req, res) -> calPage(req, res));
    }


    /**
     * Método enccargado de definir el puerto por donde corre el programa
     *
     * @return variable de tipo int
     */
    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    /**
     * Método encargado de cargar la página principal
     *
     * @param req de tipo Request
     * @param res de tipo Response
     * @return variable de tipo String
     */
    public static String calPage(Request req, Response res) {
        String pagina;

        pagina = "<DOCTYPE html"
                + "<html>"
                + "<title> Web Calculator</title>"
                + "<body>"
                + "<h1>Statistical calculator</h1>"
                + "<form action=\"/submit\">"
                + "<p>This calculator has the operations of mean and standard deviation.</p>"
                + "<p>Please follow the next recommendations  to get the most out of this tool:</p>"
                + "<ol>"
                + "<li>Type the numbers separated by commas</li>"
                + "<li>If you are using real numbers, separate the natural part and the fractional part with a period (.)</li>"
                + "</ol>"
                + "<br></br>"
                + "<label for=\"fdata\">Data set:</label>"
                + "<input type=\"text\" id=\"fdata\" name=\"fdata\"><br><br>"
                + "<input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "</body>"
                + "</html>";
        return pagina;
    }

    /**
     * Método encargado de cargar la página con los resultados de las operaciones
     *
     * @param req de tipo Request
     * @param re  de tipo Response
     * @return variable de tipo String
     * @throws Exception
     */
    public static String getData(Request req, Response re) throws Exception {
        String request = req.queryParams("fdata");
        String[] data = request.split(",");
        Calculadora calculadora = new Calculadora(data);

        String pagina;
        pagina = "<DOCTYPE html"
                + "<html>"
                + "<title> Web Calculator</title>"
                + "<body>"
                + "<h1>Results</h1>"
                + "<h3>Data set : " + request + "</h3>"
                + "<h3>Mean : " + calculadora.media() + "</h3>"
                + "<h3>Standard deviation : " + calculadora.devEstandar() + "</h3>"
                + "</body>"
                + "</html>";
        return pagina;
    }
}
