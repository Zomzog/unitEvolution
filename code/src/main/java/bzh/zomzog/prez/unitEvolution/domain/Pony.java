package bzh.zomzog.prez.unitEvolution.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

        public static PonyBuilder aPony() {
            return new PonyBuilder();
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
}
