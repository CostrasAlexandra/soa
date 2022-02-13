package ubb.soa.app

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.persistence.*
import javax.ws.rs.HeaderParam

@Entity
@Table(name = "users")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: UUID? = null,
        val username: String,
        val password: String
)

interface UserRepository:JpaRepository<User, UUID>{
    fun findByUsername(username: String):User
    fun findByUsernameAndPassword(username: String, password: String): User
}

@RestController
@RequestMapping("/users")
class UserController(
        val userRepository: UserRepository
) {
    @GetMapping
    fun getUser(@HeaderParam("username") username: String): User {
        return userRepository.findByUsername(username)
    }

    fun login(@RequestParam("username") username: String, @RequestParam("password") password:String):User{
        return userRepository.findByUsernameAndPassword(username, password)
    }
}


