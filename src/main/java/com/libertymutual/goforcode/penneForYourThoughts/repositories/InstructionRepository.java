package com.libertymutual.goforcode.penneForYourThoughts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libertymutual.goforcode.penneForYourThoughts.models.Instruction;

@Repository
public interface InstructionRepository extends JpaRepository<Instruction, Long> {

}
