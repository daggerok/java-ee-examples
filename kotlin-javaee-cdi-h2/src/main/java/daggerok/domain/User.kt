package daggerok.domain

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class User(@Id
                @GeneratedValue(generator = "UUID2")
                @GenericGenerator(name = "UUID2", strategy = "org.hibernate.id.UUIDGenerator")
                var id: UUID? = null,
                var name: String? = null)
