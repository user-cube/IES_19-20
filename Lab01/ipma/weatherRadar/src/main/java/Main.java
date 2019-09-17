import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Main class.
 */
public class Main

{
    /**
     * Asks the API the values
     * of forecast and generate
     * an output based on it.
     *
     * @param args - City ID
     */
    public static void main(String[] args) {

        /**
         * Open the connection with the database.
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        int CITY_ID = 0;

        try {
            CITY_ID = Integer.parseInt(args[0]);
        } catch (Exception e){
            System.err.println("ERROR: No argument");
            System.exit(1);
        }

        IpmaService service = retrofit.create(IpmaService.class);
        Call<IpmaCityForecast> callSync = service.getForecastForACity(CITY_ID);

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

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

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
