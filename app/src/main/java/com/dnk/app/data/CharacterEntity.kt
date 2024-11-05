import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var name: String? = null,
    var strength: Int,
    var dexterity: Int,
    var constitution: Int,
    var intelligence: Int,
    var wisdom: Int,
    var charisma: Int
) : Serializable
