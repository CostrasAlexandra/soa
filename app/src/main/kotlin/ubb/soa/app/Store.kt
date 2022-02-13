package ubb.soa.app

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.persistence.*
import javax.ws.rs.HeaderParam

@Entity
@Table(name = "items")
class Item(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: UUID? = null,
    val name: String,
    val price: Int,
    val userId: UUID
)

interface ItemRepository:JpaRepository<Item, UUID>{
    fun findAllByUserId(userId: UUID):List<Item>
}

@RestController
@RequestMapping("/items")
class ItemController(
    val itemRepository: ItemRepository
) {
    @GetMapping
    fun getAllForUserId(@HeaderParam("userId") userId: UUID): List<Item> {
        return itemRepository.findAllByUserId(userId)
    }
}


