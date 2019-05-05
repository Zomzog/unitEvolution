package bzh.zomzog.prez.unitEvolution.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Pony {

    @Id
    private ObjectId id;

    private String name;

    private PonyType type;

    public static PonyBuilder newBuilder() {
        return new PonyBuilder();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PonyType getType() {
        return type;
    }

    public void setType(PonyType type) {
        this.type = type;
    }

    public static final class PonyBuilder {
        private ObjectId id;
        private String name;
        private PonyType type;

        private PonyBuilder() {
        }

        public PonyBuilder id(ObjectId id) {
            this.id = id;
            return this;
        }

        public PonyBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PonyBuilder type(PonyType type) {
            this.type = type;
            return this;
        }

        public Pony build() {
            Pony pony = new Pony();
            pony.setId(id);
            pony.setName(name);
            pony.setType(type);
            return pony;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pony pony = (Pony) o;
        return Objects.equals(id, pony.id) &&
                Objects.equals(name, pony.name) &&
                type == pony.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }

    @Override
    public String toString() {
        return "Pony{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
