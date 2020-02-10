package hdn.example.springbatchexemple.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hdn.example.springbatchexemple.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
