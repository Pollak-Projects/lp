package com.learningpulse.administration.user;

import com.learningpulse.administration.user.dto.UserDTO;
import com.learningpulse.administration.user.dto.UserRepresentationDTO;
import com.learningpulse.administration.user.dto.UserRequestDTO;
import com.learningpulse.administration.user.dto.UserResponseDTO;
import jakarta.ws.rs.Produces;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import jakarta.ws.rs.core.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/administration/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/count")
    @ResponseStatus(HttpStatus.OK)
    public Integer getNumberOfUsers() {
        return userService.countUsers();
    }

    @GetMapping(params = {"search", "page", "size"})
    @ResponseStatus(HttpStatus.OK)
    public List<UserRepresentationDTO> listUsers(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ) {
        return userService.listUsers(search, page, size);
    }

    @GetMapping(params = {"id"})
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUserById(@RequestParam String id) {
        return userService.getUserById(id);
    }

    @PostMapping
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> createUser(@RequestBody UserRequestDTO request) {
        var resp = userService.createUser(request);

        return ResponseEntity.status(resp.status()).body(resp.message());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody UserRequestDTO request) {
        userService.updateUser(request);
    }

    @DeleteMapping(params = {"id"})
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> deleteUserById(@RequestParam String id) {
        final UserResponseDTO resp = userService.deleteUserById(id);
        return ResponseEntity.status(resp.status()).body(resp.message());
    }
}