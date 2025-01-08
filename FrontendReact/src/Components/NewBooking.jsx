import React, { useState, useEffect } from "react";
import { Modal, Button, Select, Input } from "antd";
import axios from "axios";
import "../Style/NewBooking.css";

const { Option } = Select;

const NewBooking = ({ visible, onClose, onSubmit }) => {
  const [formData, setFormData] = useState({
    maKhachHang: "",
    maPhong: "",
    maNhanVien: "",
    ngayNhanPhong: "",
    ngayTraPhong: "",
    giaPhongThucTe: "",
    trangThaiDatPhong: "da_dat",
  });

  const [customers, setCustomers] = useState([]);
  const [employees, setEmployees] = useState([]);
  const [availableRooms, setAvailableRooms] = useState([]);

  // Fetch danh sách khách hàng
  useEffect(() => {
    const fetchCustomers = async () => {
      try {
        const response = await axios.get("/home/khachhangs");
        setCustomers(Array.isArray(response.data) ? response.data : []);
      } catch (error) {
        console.error("Lỗi khi lấy danh sách khách hàng:", error);
      }
    };
    fetchCustomers();
  }, []);

  // Fetch danh sách nhân viên
  useEffect(() => {
    const fetchEmployees = async () => {
      try {
        const response = await axios.get("/home/nhanviens");
        setEmployees(Array.isArray(response.data) ? response.data : []);
      } catch (error) {
        console.error("Lỗi khi lấy danh sách nhân viên:", error);
      }
    };
    fetchEmployees();
  }, []);

  // Fetch danh sách phòng còn trống
  useEffect(() => {
    const fetchAvailableRooms = async () => {
      try {
        const response = await axios.get("/home/phongs");
        const available = response.data.filter(
          (room) => room.trangThaiPhong === "con_trong"
        );
        setAvailableRooms(available);
      } catch (error) {
        console.error("Lỗi khi lấy danh sách phòng:", error);
      }
    };
    fetchAvailableRooms();
  }, []);

  // Reset form
  const resetForm = () => {
    setFormData({
      maKhachHang: "",
      maPhong: "",
      maNhanVien: "",
      ngayNhanPhong: "",
      ngayTraPhong: "",
      giaPhongThucTe: "",
      trangThaiDatPhong: "da_dat",
    });
  };

  // Xử lý thay đổi dữ liệu form
  const handleChange = (name, value) => {
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  // Xử lý gửi form
  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(formData);
    resetForm(); // Reset lại form sau khi submit thành công
    onClose();
  };

  // Xử lý khi modal bị đóng
  const handleClose = () => {
    resetForm(); // Reset form khi đóng modal
    onClose();
  };

  return (
    <Modal
      title="Đặt phòng mới"
      visible={visible}
      onCancel={handleClose}
      footer={null}
      className="new-booking-modal"
    >
      <form className="new-booking-form" onSubmit={handleSubmit}>
        {/* Khách hàng */}
        <div className="form-group">
          <label>Khách hàng (Mã Khách Hàng)</label>
          <Select
            showSearch
            placeholder="Chọn khách hàng"
            value={formData.maKhachHang || undefined}
            onChange={(value) => handleChange("maKhachHang", value)}
            style={{ width: "100%" }}
            filterOption={(input, option) =>
              option.children.toLowerCase().includes(input.toLowerCase())
            }
          >
            {customers.map((customer) => (
              <Option key={customer.maKhachHang} value={customer.maKhachHang}>
                {customer.hoTen} ({customer.maKhachHang})
              </Option>
            ))}
          </Select>
        </div>

        {/* Phòng */}
        <div className="form-group">
          <label>Phòng (Mã Phòng)</label>
          <Select
            showSearch
            placeholder="Chọn phòng"
            value={formData.maPhong || undefined}
            onChange={(value) => handleChange("maPhong", value)}
            style={{ width: "100%" }}
            filterOption={(input, option) =>
              option.children.toLowerCase().includes(input.toLowerCase())
            }
          >
            {availableRooms.map((room) => (
              <Option key={room.maPhong} value={room.maPhong}>
                {room.loaiPhong} - {room.maPhong} (
                {room.giaPhong.toLocaleString("vi-VN")} VND)
              </Option>
            ))}
          </Select>
        </div>

        {/* Nhân viên */}
        <div className="form-group">
          <label>Nhân viên (Mã Nhân Viên)</label>
          <Select
            showSearch
            placeholder="Chọn nhân viên"
            value={formData.maNhanVien || undefined}
            onChange={(value) => handleChange("maNhanVien", value)}
            style={{ width: "100%" }}
            filterOption={(input, option) =>
              option.children.toLowerCase().includes(input.toLowerCase())
            }
          >
            {employees.map((employee) => (
              <Option key={employee.maNhanVien} value={employee.maNhanVien}>
                {employee.hoTen} ({employee.maNhanVien})
              </Option>
            ))}
          </Select>
        </div>

        {/* Ngày nhận phòng */}
        <div className="form-group">
          <label>Ngày nhận phòng</label>
          <Input
            type="date"
            name="ngayNhanPhong"
            value={formData.ngayNhanPhong}
            onChange={(e) => handleChange(e.target.name, e.target.value)}
            required
          />
        </div>

        {/* Ngày trả phòng */}
        <div className="form-group">
          <label>Ngày trả phòng</label>
          <Input
            type="date"
            name="ngayTraPhong"
            value={formData.ngayTraPhong}
            onChange={(e) => handleChange(e.target.name, e.target.value)}
            required
          />
        </div>

        {/* Giá phòng thực tế */}
        <div className="form-group">
          <label>Giá Phòng Thực Tế</label>
          <Input
            type="number"
            name="giaPhongThucTe"
            value={formData.giaPhongThucTe}
            onChange={(e) => handleChange(e.target.name, e.target.value)}
            placeholder="Nhập giá phòng thực tế"
            required
          />
        </div>

        {/* Trạng thái đặt phòng */}
        <div className="form-group">
          <label>Trạng thái đặt phòng</label>
          <Select
            value={formData.trangThaiDatPhong}
            onChange={(value) => handleChange("trangThaiDatPhong", value)}
            style={{ width: "100%" }}
          >
            <Option value="da_dat">Đã đặt</Option>
            <Option value="dang_su_dung">Đang sử dụng</Option>
          </Select>
        </div>

        {/* Nút hành động */}
        <div className="form-actions">
          <Button type="primary" htmlType="submit">
            Lưu
          </Button>
          <Button type="default" onClick={handleClose}>
            Hủy
          </Button>
        </div>
      </form>
    </Modal>
  );
};

export default NewBooking;
