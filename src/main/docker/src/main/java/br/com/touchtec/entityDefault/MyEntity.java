package br.com.touchtec.entityDefault;


import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name = "MYENTITY")
public class MyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MYENTITY")
    @SequenceGenerator(name = "SEQ_MYENTITY", sequenceName = "SEQ_MYENTITY")
    public Long id;

    @JsonbProperty("campo")
    @Column(name = "FIELD", nullable = false, length = 30)
    public String field;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        MyEntity that = (MyEntity) o;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.field, that.field);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.field);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
