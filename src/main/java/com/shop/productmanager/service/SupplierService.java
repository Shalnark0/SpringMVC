package com.shop.productmanager.service;

import com.shop.productmanager.exception.SupplierNotFoundException;
import com.shop.productmanager.model.Supplier;
import com.shop.productmanager.repo.SupplierRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SupplierService {
    private final SupplierRepo supplierRepo;

    @Autowired
    public SupplierService(SupplierRepo supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

    public Supplier addSupplier(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    public List<Supplier> findAllSuppliers() {
        return supplierRepo.findAll();
    }

    public Supplier updateSupplier(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    public Supplier findSupplierById(final Long id) {
        return supplierRepo.findById(id)
                .orElseThrow(() -> new SupplierNotFoundException("Supplier by id " + id + " not found"));
    }

    public void deleteSupplier(Long id) {
        if (id == null) {
            throw new NullPointerException("id can't be null");
        }
        supplierRepo.deleteById(id);
    }
}
