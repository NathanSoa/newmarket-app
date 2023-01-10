package com.activity.newmarketapp.config.bootstrap;

import com.activity.newmarketapp.data.entities.*;
import com.activity.newmarketapp.data.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;
    private final ProductItemRepository productItemRepository;
    private final CategoryRepository categoryRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {

        if(dataNotLoaded())
            loadData();
    }

    private boolean dataNotLoaded() {
        return roleRepository.count() == 0;
    }
    private void loadData() throws IOException {
        Role userRole = new Role();
        userRole.setRoleName(RoleName.USER);
        userRole = roleRepository.save(userRole);

        Role adminRole = new Role();
        adminRole.setRoleName(RoleName.ADMINISTRATOR);
        adminRole = roleRepository.save(adminRole);

        User user = new User();
        user.setUsername("username");
        user.setPassword(encoder.encode("password"));
        user.setRoles(new HashSet<>());
        user.getRoles().add(userRole);
        user = userRepository.save(user);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("adminPassword"));
        admin.setRoles(new HashSet<>());
        admin.getRoles().add(adminRole);
        admin = userRepository.save(admin);

        Category category = new Category();
        category.setName("First Category");
        category = categoryRepository.save(category);

        Category category1 = new Category();
        category1.setName("Second Category");
        category1 = categoryRepository.save(category1);

        Product product = new Product();
        product.setName("First Product");
        product.setDescription("This is my new product by the way");
        product.setPrice(BigDecimal.valueOf(10L));
        product.setActive(false);
        product.setCategories(new HashSet<>(List.of(category, category1)));

        productRepository.save(product);

        Product product2 = new Product();
        product2.setName("Second Product");
        product2.setDescription("This is my second product by the way");
        product2.setPrice(BigDecimal.valueOf(20L));
        product2.setActive(true);
        product2.setCategories(new HashSet<>(List.of(category)));

        productRepository.save(product2);

        Product product3 = new Product();
        product3.setName("Third Product");
        product3.setDescription("This is my third product by the way");
        product3.setPrice(BigDecimal.valueOf(30L));
        product3.setActive(false);

        productRepository.save(product3);

        ProductItem productItem = new ProductItem();
        productItem.setProduct(productRepository.findById(1L).get());
        productItem.setUser(user);
        productItem.setQuantity(10);

        productItemRepository.save(productItem);

        ProductItem productItem2 = new ProductItem();
        productItem2.setProduct(productRepository.findById(2L).get());
        productItem2.setUser(user);
        productItem2.setQuantity(12);

        productItemRepository.save(productItem2);
    }
}
