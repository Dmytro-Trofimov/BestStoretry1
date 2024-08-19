package com.Dmytro.BestStore.BestStoreTry1.Controller;


import java.nio.file.Files;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Dmytro.BestStore.BestStoreTry1.Repository.ProductRepository;
import com.Dmytro.BestStore.BestStoreTry1.entity.Product;
import com.Dmytro.BestStore.BestStoreTry1.entity.ProductDTO;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class MController {
    @Autowired
    private ProductRepository repo;

    @GetMapping({"/", ""})
    public String homePage(Model model) {
        List<Product> list = repo.findAll();
        model.addAttribute("products", list);
        return "products/home";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("prdto", new ProductDTO());
        return "products/CreateProduct";
    }

    @PostMapping("/create")
    public String createProduct(@Valid @ModelAttribute("prdto") ProductDTO prdto, BindingResult br) {
        if (prdto.getImage_file_name() == null) {
            br.addError(new FieldError("prdto", "image_file_name", "The image file is empty"));
        }
        System.out.println(br.toString());
        if (br.hasErrors()) {
            return "products/CreateProduct";
        }
        
        MultipartFile file = prdto.getImage_file_name();
        Date date = new Date();
        String storageFileName = date.getTime() + "_" + file.getOriginalFilename();
        
        //save image 
        try {
        	String uploadDir = "C:/Users/Dmytro/eclipse-workspace/BestStoreTry1/public/images/";
        	Path uploadPath = Paths.get(uploadDir);
        	
        	if(!Files.exists(uploadPath)) {
        		Files.createDirectories(uploadPath);
        	}
        	
        	try (InputStream inputStream = file.getInputStream()){
        		Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
        	}
        	
        }catch(Exception e) {
        	System.out.println("EEException: "+e);
        }
        
        Product product = new Product();
        product.setName(prdto.getName());
        product.setBrand(prdto.getBrand());
        product.setCategory(prdto.getCategory());
        product.setCreated_at(date);
        product.setDescription(prdto.getDescription());
        product.setImage_file_name(storageFileName.substring(0, storageFileName.length()-4));
        product.setPrice(prdto.getPrice());
        
        repo.save(product);
        
        System.out.println(prdto);
        return "redirect:/products";
    }
	@GetMapping("/delete")
	public String delete(@RequestParam int id) {
		repo.deleteById(id);
		return "redirect:";
	}
	@GetMapping("/edit")
    public String updateProduct(@RequestParam int id, Model model) {
		Product product = repo.findById(id).get();
        model.addAttribute("product", product);
        
        
        ProductDTO prdto = new ProductDTO();
        prdto.setName(product.getName());
        prdto.setBrand(product.getBrand());
        prdto.setCategory(product.getCategory());
        prdto.setDescription(product.getDescription());
        prdto.setPrice(product.getPrice());
        model.addAttribute("prdto", prdto);
        
        return "products/UpdateProduct";
    }
		@PostMapping("/edit")
	    public String updateProduct(@RequestParam int id, @Valid @ModelAttribute("prdto") ProductDTO prdto, BindingResult br, Model model) {
			
		try {
			Product product = repo.findById(id).get();
			model.addAttribute("product",product);
			
			if(br.hasErrors()) {
				return "products/UpdateProduct";
			}
			MultipartFile file = prdto.getImage_file_name();
	        if (file != null && !file.isEmpty()){
				String uploadDir = "C:/Users/Dmytro/eclipse-workspace/BestStoreTry1/public/images/";
				Path oldPath = Paths.get(uploadDir + product.getImage_file_name());				
				try {
					Files.delete(oldPath);
				}catch(Exception e){
					System.out.println("e blya " + e);
				}
				
/*!!!!!!!!*/	Date date = new Date();
				String originalFilename = file.getOriginalFilename();

				String storageFileName = date.getTime() + "_" + originalFilename;
				
				System.out.println("Date Time: " + date.getTime());
				System.out.println("Original Filename: " + originalFilename);
				System.out.println("Storage Filename: " + storageFileName);
		        try (InputStream inputStream = file.getInputStream()){
	        		Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
	        	}
		        product.setImage_file_name(storageFileName.substring(0, storageFileName.length()-4));
			}
			product.setName(prdto.getName());
	        product.setBrand(prdto.getBrand());
	        product.setCategory(prdto.getCategory());
	        product.setDescription(prdto.getDescription());
	        product.setPrice(prdto.getPrice());
	        
	        repo.save(product);
			
		} catch (Exception e) {
			System.out.println("EException " + e);
		}
        
        return "redirect:";
    }
}
