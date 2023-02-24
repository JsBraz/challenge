package com.challenge.challenge.services;

import com.challenge.challenge.models.Pathology;
import com.challenge.challenge.repository.PathologyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PathologyService {
    private PathologyRepo pathologyRepo;

    @Autowired
    public PathologyService(PathologyRepo pathologyRepo) {
        this.pathologyRepo = pathologyRepo;
    }

    public Iterable<Pathology> findAll() {
        return this.pathologyRepo.findAll();
    }

    public Optional<Pathology> findById(Long id) {
        return this.pathologyRepo.findById(id);
    }

    public Optional<Pathology> createPathology(Pathology pathology) {
        Optional<Pathology> optionalPathology = this.pathologyRepo.findByName(pathology.getName());
        if (optionalPathology.isPresent()) {
            return Optional.empty();
        }
        Pathology createdPathology = this.pathologyRepo.save(pathology);
        return Optional.of(createdPathology);
    }

    public Optional<Pathology> findByName(String name) {
        return this.pathologyRepo.findByName(name);
    }

    public void deletePatient(Optional<Pathology> pathology) {
        pathologyRepo.delete(pathology.get());
    }

    public Pathology save(Pathology pathology) {
        return pathologyRepo.save(pathology);
    }
}

