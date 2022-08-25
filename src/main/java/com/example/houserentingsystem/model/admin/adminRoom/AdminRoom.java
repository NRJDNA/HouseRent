package com.example.houserentingsystem.model.admin.adminRoom;

import com.example.houserentingsystem.enums.RoomStatus;
import com.example.houserentingsystem.enums.RoomType;
import com.example.houserentingsystem.model.admin.AdminRegister;
import com.example.houserentingsystem.model.register.Register;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author dangal_nirajan on 10/08/2022
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "admin_adminRoom")
public class AdminRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "AdminRoom_SEQ_GEN")
    @SequenceGenerator(name = "AdminRoom_SEQ_GEN", sequenceName = "AdminRoom_SEQ", allocationSize = 1)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="contact")
    private String contact;
    @Column(name = "address", nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private RoomType roomType;

    @Column
    private RoomStatus roomStatus;

    @Temporal(TemporalType.DATE)
    @Column(name = "adminRoom_date")
    private Date adminRoomDate;

    @Column(name="Price")
    private String price;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "admin_register_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "Fk_register_adminRoom"))
    private AdminRegister adminRegister;
}


