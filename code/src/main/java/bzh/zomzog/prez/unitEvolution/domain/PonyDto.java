package bzh.zomzog.prez.unitEvolution.domain;

public class PonyDto {

    private String id;
    private String name;
    private PonyType type;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PonyType getType() {
        return type;
    }

    public PonyDto(String id, String name, PonyType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public static PonyDtoBuilder newBuilder() {
        return new PonyDtoBuilder();
    }

    public static final class PonyDtoBuilder {
        private String id;
        private String name;
        private PonyType type;

        private PonyDtoBuilder() {
        }

        public PonyDtoBuilder id(String id) {
            this.id = id;
            return this;
        }

        public PonyDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PonyDtoBuilder type(PonyType type) {
            this.type = type;
            return this;
        }

        public PonyDto build() {
            return new PonyDto(id, name, type);
        }
    }
}
