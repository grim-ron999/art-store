package com.artstore.config;

import com.artstore.model.Art;
import com.artstore.repository.ArtRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ArtRepository artRepository;

    public DataLoader(ArtRepository artRepository) {
        this.artRepository = artRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (artRepository.count() == 0) {
            Art a1 = new Art();
            a1.setTitle("Midnight Dreams");
            a1.setCategory("Digital Art");
            a1.setPrice(149.99);
            a1.setImageUrl("https://via.placeholder.com/800x600.png?text=Midnight+Dreams");
            a1.setDescription("A moody digital piece exploring night and light.");
            artRepository.save(a1);

            Art a2 = new Art();
            a2.setTitle("Sunrise Fields");
            a2.setCategory("Painting");
            a2.setPrice(249.00);
            a2.setImageUrl("https://via.placeholder.com/800x600.png?text=Sunrise+Fields");
            a2.setDescription("Warm landscape painting full of energy.");
            artRepository.save(a2);

            Art a3 = new Art();
            a3.setTitle("City Lines");
            a3.setCategory("Illustration");
            a3.setPrice(99.50);
            a3.setImageUrl("https://via.placeholder.com/800x600.png?text=City+Lines");
            a3.setDescription("Minimalist city illustration in monochrome.");
            artRepository.save(a3);
        }
    }
}
