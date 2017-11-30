package br.edu.ulbra.gestaoloja.controller;

import java.io.IOException;
import java.util.List;

import java.io.File;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ulbra.gestaoloja.input.ProdutoInput;
import br.edu.ulbra.gestaoloja.model.Produto;
import br.edu.ulbra.gestaoloja.repository.ProductRepository;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	
	private ModelMapper mapper = new ModelMapper();
	
	@Autowired
    ProductRepository produtoRepository;
	
	@Value("${gestao-loja.uploadFilePath}")
	private String uploadFilePath;

	@GetMapping()
    public ModelAndView listarProdutos() {
        ModelAndView mv = new ModelAndView("produto/list");
        List<Produto> produtos = (List<Produto>) produtoRepository.findAll();
        
        mv.addObject("produtos", produtos);
        return mv;
    }
    
    @GetMapping("/new")
    public ModelAndView novoProduto() {
    	ProdutoInput produtoInput = new ProdutoInput();
    	ModelAndView mv = new ModelAndView("produto/new");
        mv.addObject("produto", produtoInput);
        return mv;
    }
    
    @PostMapping(value="/new")
    public ModelAndView novoProduto(ProdutoInput produtoInput) throws IOException {
        Produto produto = mapper.map(produtoInput, Produto.class);
        
        MultipartFile multipartFile = produtoInput.getMultipartFile();
        String fileName = produtoInput.getMultipartFile().getOriginalFilename();

        if (multipartFile != null){
            File file = new File(this.uploadFilePath+"\\"+fileName);
            file.createNewFile();
            multipartFile.transferTo(file);
        }
        
        produto.setImagePath(fileName);
        produto.setNome(produtoInput.getNome());
        produto.setDescricao(produtoInput.getDescricao());
        
        produtoRepository.save(produto);
        
        
       
        return new ModelAndView("redirect:/produto");
    }
    
    @GetMapping("/{id}")
    public ModelAndView visualizarProduto(@PathVariable(name="id") Long id) {
    	Produto produto = produtoRepository.findOne(id);
    	ProdutoInput produtoInput = mapper.map(produto, ProdutoInput.class);
    	
        ModelAndView mv = new ModelAndView("produto/update");
        mv.addObject("produto", produtoInput);
        
        return mv;
    }

    @PostMapping("/{id}")
    public ModelAndView atualizarProduto(@PathVariable(name="id") Long id, ProdutoInput produtoInput) {
    	Produto produto = produtoRepository.findOne(id);
    	
    	produto.setDescricao(produtoInput.getDescricao());
    	produto.setNome(produtoInput.getNome());
    	
    	produtoRepository.save(produto);
    	
    	return new ModelAndView("redirect:/produto");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView excluirProduto(@PathVariable(name="id") Long id) {
    	Produto produto = produtoRepository.findOne(id);
    	produtoRepository.delete(produto);
    	
    	return new ModelAndView("redirect:/produto");
    }
    
    @GetMapping("/files/{fileName:.+}")
    @ResponseBody
    public FileSystemResource getFile(@PathVariable("fileName") String fileName) {
    	FileSystemResource fileSystemResource = new FileSystemResource(this.uploadFilePath + "/" + fileName);
    	return fileSystemResource;
    }
}
