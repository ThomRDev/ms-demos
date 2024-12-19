package $org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Configura ChromeDriver automáticamente

        WebDriverManager.chromedriver().setup();



        // Inicia el navegador

        WebDriver driver = new ChromeDriver();



        try {

            // Navega a la página de login

            driver.get("https://www.ejemplo.com/login");



            // Maximiza la ventana del navegador

            driver.manage().window().maximize();



            // Localiza el campo de usuario y contraseña

            WebElement username = driver.findElement(By.id("username"));

            WebElement password = driver.findElement(By.id("password"));



            // Ingresa credenciales

            username.sendKeys("mi_usuario");

            password.sendKeys("mi_contraseña");



            // Clic en el botón de login

            WebElement loginButton = driver.findElement(By.id("loginButton"));

            loginButton.click();



            // Verifica que el login fue exitoso

            if (driver.getCurrentUrl().contains("/dashboard")) {

                System.out.println("✅ Login exitoso");

            } else {

                System.out.println("❌ Login fallido");

            }



        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            // Cierra el navegador

            driver.quit();

        }
    }
}
