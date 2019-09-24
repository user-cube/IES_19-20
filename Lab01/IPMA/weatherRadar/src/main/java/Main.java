import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import org.apache.logging.log4j.*;

import java.io.IOException;

/**
 * Main class.
 */
public class Main

{
    private static final Logger logger = LogManager.getLogger("WeatherRadar.log");
    /**
     * Asks the API the values
     * of forecast and generate
     * an output based on it.
     *
     * @param args - City ID
     */
    public static void main(String[] args) {

        /**
         * Verifies if we only have one
         * argument.
         */
        if (args.length != 1){
            logger.error("Invalid arguments");
            System.exit(1);
        }

        /**
         * Retrofit initialization.
         */
        Retrofit retrofit = null;
        /**
         * Flag to define if the input
         * is the id of the city or
         * the city name.
         */
        Boolean isNumber = true;
        /**
         * Instantiation of IPMA forecast.
         */
        IpmaCityForecast forecast;
        /**
         * Initialization of callSync of IpmaCityForecast.
         */
        Call<IpmaCityForecast> callSync = null;
        /**
         * Initialization of apiResponse of IpmaCityForecast.
         */
        Response<IpmaCityForecast> apiResponse = null;
        /**
         * City ID.
         */
        int CITY_ID = 0;

        /**
         * Open the connection with the database.
         */
        try {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.ipma.pt/open-data/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            logger.info("Connection established successfully");
        } catch (Exception e) {
            logger.error("Unable to establish connection", e);
            System.exit(1);
        }
        /**
         * If the argument is an int we
         * call the API, otherwise
         * we skip the
         */
        try {
            CITY_ID = Integer.parseInt(args[0]);
            IpmaService service = retrofit.create(IpmaService.class);
            callSync = service.getForecastForACity(CITY_ID);
        } catch (Exception e){
            isNumber = false;
            logger.info("Input: isNumber = false", e);
        }
        /**
         * If the input is a number we call the api
         * with the globalIdLocal, otherwise we
         * call "http://api.ipma.pt/open-data/distrits-islands.json"
         * and we find the id and after that we call
         * "forecast/meteorology/cities/daily/{city_id}.json"
         */
        if (isNumber){
            try {
                apiResponse = callSync.execute();
                logger.info("Call api");
            } catch (IOException e) {
                logger.error("Failed call", e);
                System.exit(1);
            }
            forecast = apiResponse.body();
            logger.info("API Response");
        } else {
            IpmaService service = retrofit.create(IpmaService.class);
            Call<IPMAInformation> callName = service.getIDByName();
            try {
                Response<IPMAInformation> getID = callName.execute();
                IPMAInformation ID = getID.body();
                CITY_ID = Integer.parseInt(ID.getData(args[0]));
            } catch (IOException e) {
                logger.error("Unable to communicate with API", e);
            }
            callSync = service.getForecastForACity(CITY_ID);
            try {
                apiResponse = callSync.execute();
                logger.info("Call api");
            } catch (IOException e) {
                logger.error("Failed call", e);
            }
            forecast = apiResponse.body();
            logger.info("API Response");
        }

        /**
         * If everything is ok we display the
         * information to the users.
         */
        System.out.println( "Country: " + forecast.getCountry() + "\n" +
                "Source: " + forecast.getOwner() + "\n" +
                "Local ID: " + forecast.getGlobalIdLocal() + "\n" +
                "Latitude: " + forecast.getData().listIterator().next().getLatitude() + "\n" +
                "Longitude: " + forecast.getData().listIterator().next().getLongitude() + "\n" +
                "Min Temperature: " + forecast.getData().listIterator().next().getTMin() + "ºC\n" +
                "Max Temperature: " + forecast.getData().listIterator().next().getTMax() + "ºC\n" +
                "Precipitation probability: " + forecast.getData().listIterator().next().getPrecipitaProb() + "%\n" +
                "Win direction: " + forecast.getData().listIterator().next().getPredWindDir() + "\n" +
                "Win speed: " + forecast.getData().listIterator().next().getClassWindSpeed() + "\n" +
                "Last Update: " + forecast.getDataUpdate() + "\n");

        System.out.println( );
        logger.info("Information successfully got");

    }
}
