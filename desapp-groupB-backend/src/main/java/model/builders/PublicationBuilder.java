package model.builders;

import model.publication.Publication;

public class PublicationBuilder {

    private Publication buildPublication;

    public PublicationBuilder createPublication() {
        this.buildPublication = new Publication();
        return this;
    }

    public Publication build() {
        return buildPublication;
    }
}
