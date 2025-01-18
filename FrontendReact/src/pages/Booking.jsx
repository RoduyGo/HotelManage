import React, { useEffect, useState } from "react";
import { Table, Input, Button, notification, Modal } from "antd";
import axios from "axios";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash, faRectangleList } from "@fortawesome/free-solid-svg-icons";
import NewBooking from "../Components/NewBooking";
import ServiceAdd from "../Components/ServiceAdd";
import "../Style/Booking.css";
const Booking = () => {
  const [bookings, setBookings] = useState([]);
  const [filteredBookings, setFilteredBookings] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedBooking, setSelectedBooking] = useState(null);
  const [thongTin, setThongTin] = useState(false);
  const [isAdding, setIsAdding] = useState(false);
  const [usingService, setUsingService] = useState(false);

  useEffect(() => {
    fetchBookings();
  }, []);
  const fetchBookings = async () => {
    try {
      const response = await axios.get("home/booking");
      const data = response.data;
      setBookings(data);
      setFilteredBookings(data);
    } catch (error) {
      console.error(" Lỗi khi lấy booking", error);
    }
  };
  const fetchBookingDetail = async (maDatPhong) => {
    try {
      const response = await axios.get(`home/booking/${maDatPhong}`);
      setSelectedBooking(response.data); 
    } catch (error) {
      console.error("Lỗi khi lấy chi tiết booking:", error);
      notification.error({
        message: "Lỗi",
        description: "Không thể tải chi tiết booking.",
      });
    }
  };
  
  const handleSearch = (e) => {
    const value = e.target.value.toLowerCase();
    setSearchTerm(value);
    const filtered = bookings.filter((booking) =>
      booking.khachHang.hoTen.toLowerCase().includes(value)
    );
    setFilteredBookings(filtered);
  };
  const columns = [
    {
      title: "Mã Đặt Phòng",
      dataIndex: "maDatPhong",
      key: "maDatPhong",
    },
    {
      title: "Tên Khách Hàng",
      key: "khachHang",
      render: (_, record) => record.khachHang.hoTen,
    },
    {
      title: "Mã Phòng",
      key: "maPhong",
      render: (_, record) => record.phong.maPhong,
    },
    {
      title: "Trạng thái",
      key: "trangThaiDatPhong",
      render: (_, record) => translateTrangThai(record.trangThaiDatPhong),
    },
    {
      title: "Hành động",
      key: "action",
      render: (_, record) => (
        <div style={{ display: "flex", alignItems: "center", gap: "10px" }}>
          <Button
            type="button"
            className="detailBooking-button"
            style={{ fontSize: "26px", padding: "0", margin: "0" }}
            onClick={() => handleViewTT(record)}
            icon={<FontAwesomeIcon icon={faRectangleList} />}
          />
          <Button
            type="button"
            className="delete-button"
            style={{ fontSize: "20px", padding: "0", margin: "0" }}
            onClick={() => handleDelete(record.maDatPhong)}
            icon={<FontAwesomeIcon icon={faTrash} />}
          />
        </div>
      ),
    },
  ];
  
  const handleDelete = (maDatPhong) => {
    Modal.confirm({
      title: "Xác nhận xóa",
      content: ` Bạn có chắc chắc muốn xóa Booking với id ${maDatPhong}`,
      okText: "Xóa",
      okType: "danger",
      cancelText: "Hủy",
      onOk: () => {
        axios
          .delete(`home/booking/${maDatPhong}`)
          .then(() => {
            notification.success({
              message: "Xóa thành công",
              description: `Booking ${maDatPhong} đã được xóa thành công`,
            });
            fetchBookings();
          })
          .catch((error) => {
            console.error("Lỗi xóa booking", error);
          });
      },
    });
  };
  const handleViewTT = async(bookings) => {
    await fetchBookingDetail(bookings.maDatPhong); 
    setThongTin(true);
  };
  const handleCloseView = () => {
    setThongTin(false);
    setSelectedBooking(null);
  };
  const handleAddBooking = (values) => {
    const payload = {
      ...values,
      ngayNhanPhong: values.ngayNhanPhong,
      ngayTraPhong: values.ngayTraPhong,
    };

    axios
      .post("home/booking", payload)
      .then(() => {
        notification.success({
          message: "Thành công",
          description: "Đặt phòng mới thành công!",
        });
        console.log("Dữ liệu nhận được từ NewBooking:", values);

        fetchBookings();
        setIsAdding(false);
      })
      .catch((error) => {
        console.error("Lỗi khi tạo booking", error);
        notification.error({
          message: "Lỗi",
          description: "Không thể tạo booking mới.",
        });
      });
  };

  const handleCheckout = async (maDatPhong) => {  
    try {
      const response = await axios.put(`home/booking/${maDatPhong}`, { trangThaiDatPhong: 'da_tra_phong' })
        .then(() => {
          notification.success({
            message: "Thành công",
            description: "Trả phòng thành công",
          });
          fetchBookings();
          setIsAdding(false);
        });
    } catch (error) {
      console.error("Lỗi khi trả phòng:", error);
    }
  }
  const translateTrangThai = (trangThai) => {
    switch (trangThai) {
      case "con_trong":
        return "Còn Trống";
      case "dang_su_dung":
        return "Đang Sử Dụng";
      case "da_tra_phong":
        return "Đã Trả Phòng";
      default:
        return "Không xác định";
    }
  };
  
  return (
    <div style={{ padding: "20px" }}>
      <h1
        style={{
          fontSize: "24px",
          fontWeight: "bold",
          color: "#333",
          textAlign: "center",
        }}
      >
        Quản Lý Booking
      </h1>
      <header
        style={{
          display: "flex",
          justifyContent: "space-between",
          marginBottom: "20px",
        }}
      >
        <Input
          placeholder="Tìm kiếm tên khách hàng"
          value={searchTerm}
          onChange={handleSearch}
          style={{ width: "500px", fontStyle: "italic" }}
        />
        <Button
          type="button"
          className="add-button"
          onClick={() => setIsAdding(true)}
        >
          <i class="fa-solid fa-cart-plus"></i>
          Đặt phòng
        </Button>
        <NewBooking
          visible={isAdding}
          onClose={() => setIsAdding(false)}
          onSubmit={handleAddBooking}
        />
      </header>
      <Table
        dataSource={filteredBookings}
        columns={columns}
        rowKey="maDatPhong"
        bordered
        pagination={{ pageSize: 10 }}
      />
      <Modal
        title="Chi tiết booking"
        visible={thongTin}
        onCancel={handleCloseView}
        footer={null}
      >
        {selectedBooking && (
          <div>
            <p>Mã booking: {selectedBooking.maDatPhong}</p>

            <p>Ngày nhận phòng: {selectedBooking.ngayNhanPhong}</p>
            <p>Ngày trả phòng: {selectedBooking.ngayTraPhong}</p>
            <p>Khách hàng: {selectedBooking.khachHang.hoTen}</p>
            <p>Loại phòng: {selectedBooking.phong.loaiPhong}</p>
            <p>Nhân viên: {selectedBooking.nhanVien.hoTen}</p>
            <p>Giá Phòng thực tế: {selectedBooking.giaPhongThucTe}</p>

            <p>Dịch Vụ sử dụng:</p>
            <ul>
              {selectedBooking.suDungDichVuList.map((dichVu, index) => (
                <li key={index}>
                  Dịch vụ: {dichVu.dichVu.tenDichVu}, Giá:{" "}
                  {dichVu.dichVu.giaDichVu}, Số lượng: {dichVu.soLuong}
                </li>
              ))}
            </ul>
            <div
  style={{
    marginTop: "20px",
    display: "flex",
    justifyContent: "space-between",
  }}
>
  {selectedBooking.trangThaiDatPhong !== "da_tra_phong" && (
    <Button
      type="button"
      className="service-button"
      onClick={() => setUsingService(true)}
    >
      Sử dụng dịch vụ
    </Button>
  )}
  <Button
    type="default"
    onClick={() => handleCheckout(selectedBooking.maDatPhong)}
  >
    Trả Phòng
  </Button>
</div>

          </div>
        )}
      </Modal>

      <ServiceAdd
        visible={usingService}
        onClose={() => setUsingService(false)}
        maDatPhong={selectedBooking?.maDatPhong}
        onServiceAdded={() => fetchBookingDetail(selectedBooking?.maDatPhong)}
      />
    </div>
  );
};
export default Booking;
