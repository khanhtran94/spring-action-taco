package com.example.tacoclound.data;

import com.example.tacoclound.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
