package com.appRacer.Run.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appRacer.Run.model.Usuario;
import com.appRacer.Run.repository.UsuarioRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PostMapping("cadastrar")
	public ResponseEntity<Usuario> salvarUsario(@RequestBody @Valid Usuario usuario) {
		usuarioRepository.save(usuario);
		return new ResponseEntity<>(usuario, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> encontrarUsuario(@PathVariable UUID idUser) {
		Optional<Usuario> usuario = usuarioRepository.findById(idUser);
		if(usuario.isPresent()) {
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		}
		return new ResponseEntity<>("Usuario nao encontrado", HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("deletar/{id}")
	public ResponseEntity<String> deletarUsuario(@PathVariable UUID idUser) {
		Optional<Usuario> usuario = usuarioRepository.findById(idUser);
		if(usuario.isPresent()) {
			usuarioRepository.deleteById(idUser);
			return new ResponseEntity<>("Usuario deletado com sucesso", HttpStatus.OK);
		}
		return new ResponseEntity<>("Usuario nao encontrado", HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("editar/{id}")
	public ResponseEntity<?> editarUsario(@RequestBody @Valid Usuario usuario, @PathVariable UUID idUser) {
		Optional<Usuario> encontradoOpt = usuarioRepository.findById(idUser);
		if(encontradoOpt.isPresent()) {
			Usuario encontrado = encontradoOpt.get();
			encontrado.setNomeCompleto(usuario.getNomeCompleto());
            encontrado.setCpf(usuario.getCpf());
            encontrado.setIdade(usuario.getIdade());
            encontrado.setPesoKg(usuario.getPesoKg());
            encontrado.setAlturaCm(usuario.getAlturaCm());
            encontrado.setImc(usuario.getImc()); 
            
            return new ResponseEntity<>(encontrado, HttpStatus.OK);

		}
		return new ResponseEntity<>("Usario nao encontrado", HttpStatus.NOT_FOUND);
	}
	
	
}
