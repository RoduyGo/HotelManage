import React from "react";
import BaoCaoDT from "../Components/BaoCaoDT";
import BaoCaoP from "../Components/BaoCaoP";

const Dashboard = () => {
  return (
    <div
      style={{
        display: 'flex',
        flexDirection: 'column',
        height: '100vh',
        padding: '20px',
        gap: '20px',
      }}
    >

      <div
        style={{
          flex: 1,
          overflowY: 'auto',
          borderBottom: '1px solid #ddd',
          paddingBottom: '10px',
          display: 'flex',
          flexDirection: 'column',
        }}
      >
        <h2 style={{ textAlign: 'center', marginBottom: '20px' }}>Báo cáo Doanh thu</h2>
        <BaoCaoDT />
      </div>


      <div
        style={{
          flex: 1,
          overflowY: 'auto',
          display: 'flex',
          flexDirection: 'column',
        }}
      >
        <h2 style={{ textAlign: 'center', marginBottom: '20px' }}>Báo cáo Loại Phòng được ưa chuộng</h2>
        <BaoCaoP />
      </div>
    </div>
  );
};

export default Dashboard;
