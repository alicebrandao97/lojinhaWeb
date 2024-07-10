package Login.paginas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioDeAdicaoDeProdutosPage {
    private WebDriver navegador;

    public FormularioDeAdicaoDeProdutosPage(WebDriver navegador) {
        this.navegador = navegador;
    }

    public FormularioDeAdicaoDeProdutosPage informarNomeDoProduto(String ProdutoNome) {
        navegador.findElement(By.id("produtonome")).sendKeys(ProdutoNome);
        return this;
    }

    public FormularioDeAdicaoDeProdutosPage informarValorDoProduto(String ProdutoValor) {
        navegador.findElement(By.id("produtovalor")).sendKeys(ProdutoValor);
        return this;
    }

    public FormularioDeAdicaoDeProdutosPage informarCoresDoProduto(String ProdutoCores) {
        navegador.findElement(By.id("produtocores")).sendKeys(ProdutoCores);
        return this;
    }

    public ListaDeProdutosPage submeterFormularioDeAdicaoComErro() {
        navegador.findElement(By.cssSelector("button[type='submit']")).click();
        return new ListaDeProdutosPage(navegador);
    }

    public FormularioDeEdicaoDeProdutoPage submeterFormularioDeAdicaoComSucesso() {
        navegador.findElement(By.cssSelector("button[type='submit']")).click();
        return new FormularioDeEdicaoDeProdutoPage(navegador);
    }

    //<div class="toast rounded" style="top: 0px; transition: transform 0.2s ease 0s, opacity 0.2s ease 0s;">Produto adicionado com sucesso</div>

}
