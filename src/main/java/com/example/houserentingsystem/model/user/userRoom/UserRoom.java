package com.example.houserentingsystem.model.user.userRoom;

import com.example.houserentingsystem.enums.RoomStatus;
import com.example.houserentingsystem.enums.RoomType;
import com.example.houserentingsystem.model.register.Register;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_userRoom")
public class UserRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "UserRoom_SEQ_GEN")
    @SequenceGenerator(name = "UserRoom_SEQ_GEN", sequenceName = "UserRoom_SEQ", allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name="contact")
    private String contact;

    @Column(name = "address", nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private RoomType roomType;


    @Temporal(TemporalType.DATE)
    @Column(name = "userRoomNeed_date")
    private Date userRoomDate;

    @Column
    private RoomStatus roomStatus;

    @Column(name = "description", nullable = false)
    private String description;

    //images
    @Column
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "register_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "Fk_register_userRoom"))
    private Register register;
}

