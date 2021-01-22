package com.capg.sbs.excel;
import com.capg.sbs.excel.ExcelHelper;
import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.sbs.entity.Product;
import com.capg.sbs.repository.ProductRepository;

@Service
public class ExcelService {
  @Autowired
  ProductRepository repository;

  public ByteArrayInputStream load() {
    List<Product> products = repository.findAllProductdata();

    ByteArrayInputStream in = ExcelHelper.productsToExcel(products);
    return in;
  }
}
