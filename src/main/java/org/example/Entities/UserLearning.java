package org.example.Entities;

import javax.persistence.*;

@Entity
@Table(name = "user_learnings")
public class UserLearning {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;

        @Column(nullable = false)
        private int userId;

        @ManyToOne
        @JoinColumn(name = "learning_id", nullable = false)
        private LearningTypes learningType;

        private String proof;

        @Column(nullable = false)
        @ManyToOne
        @JoinColumn(name = "proof_id", nullable = false)
        private ProofTypes proofType;

        @ManyToOne
        @JoinColumn(name = "booster_id", nullable = false)
        private Booster booster;

        @Temporal(TemporalType.DATE)
        private int date;

        @Enumerated(EnumType.STRING)
        private ApprovalStatus approvalStatus;

        private String comment;

        public enum ApprovalStatus {
            PENDING, APPROVED, REJECTED
        }


    }
