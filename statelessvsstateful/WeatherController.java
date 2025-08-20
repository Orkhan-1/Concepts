package statelessvsstateful;

@RestController
public class WeatherController {

    // This service is stateless - it just calculates based on input.
    @GetMapping("/weather")
    public Weather getWeather(
            @RequestParam String city,
            @RequestParam String date) {

        // The method uses ONLY the parameters passed in.
        // It doesn't check if this is the same user who asked for London yesterday.
        int temperature = weatherService.calculateTemp(city, date);
        String condition = weatherService.getCondition(city, date);

        return new Weather(city, date, temperature, condition);
    }
}