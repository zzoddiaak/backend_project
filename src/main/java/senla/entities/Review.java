package senla.entities;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "reviews")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(name = "rating")
    private Integer rating;
    @Column(name = "comment_reviews")
    private String commentReviews;

    @ManyToOne(fetch=FetchType.LAZY,
            cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private Session session;

}
