package com.libertymutual.goforcode.penneForYourThoughts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libertymutual.goforcode.penneForYourThoughts.models.Instruction;

public interface InstructionRepository extends JpaRepository<Instruction, Long> {

}
