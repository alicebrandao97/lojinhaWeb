package produtos;

import Login.paginas.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.time.Duration;

@DisplayName("Testes Web do módulo de produtos")
public class ProdutosTest {
    private WebDriver navegador;

    @BeforeEach
    public void BeforeEach() {
        //Abrir o Navegador
        System.setProperty("webdriver.edge.driver", "C:\\drivers\\edgedriver_win64 (1)\\msedgedriver.exe");
        this.navegador = new EdgeDriver();
        navegador.get("http://165.227.93.41/lojinha-web/v2/");
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {
        //Fazer login
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("alicewonderland")
                .informarASenha("12345")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeNovoProduto()
                .informarNomeDoProduto("Mac Book Pro")
                .informarValorDoProduto("000")
                .informarCoresDoProduto("Preto,Branco")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor maior que 7000")
    public void testNaoEPermitidoRegistrarProdutoComValorMaiorQueSeteMil() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("alicewonderland")
                .informarASenha("12345")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeNovoProduto()
                .informarNomeDoProduto("Iphone")
                .informarValorDoProduto("700001")
                .informarCoresDoProduto("Preto")
                .submeterFormularioDeAdicaoComErro()
                .capturarMensagemApresentada();

        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);
    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam no limite de R$ 0,01")
    public void testPossoAdicionarProdutosComValorDeUmCentavo() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("alicewonderland")
                .informarASenha("12345")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeNovoProduto()
                .informarNomeDoProduto("Iphone")
                .informarValorDoProduto("001")
                .informarCoresDoProduto("Preto")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);

    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam no limite de 7.000,00")
    public void testPossoAdicionarProdutosComValorDeSeteMil() {
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("alicewonderland")
                .informarASenha("12345")
                .submeterFormularioDeLogin()
                .acessarFormularioDeAdicaoDeNovoProduto()
                .informarNomeDoProduto("Macbook Pro")
                .informarValorDoProduto("700000")
                .informarCoresDoProduto("preto")
                .submeterFormularioDeAdicaoComSucesso()
                .capturarMensagemApresentada();

        Assertions.assertEquals("Produto adicionado com sucesso", mensagemApresentada);
    }

    @AfterEach
    public void afterEach() {
        navegador.quit();
    }

}

