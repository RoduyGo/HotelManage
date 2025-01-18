import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Pie } from 'react-chartjs-2';
import {
  Chart as ChartJS,
  ArcElement,
  Tooltip,
  Legend,
} from 'chart.js';

ChartJS.register(ArcElement, Tooltip, Legend);

const BaoCaoP = () => {
  const [roomData, setRoomData] = useState({
    Luxury: 0,
    Vip: 0,
    Basic: 0,
  });

  useEffect(() => {
    axios
      .get('/home/hoadon/baocaoPhong')
      .then((response) => {
        const newData = { Luxury: 0, Vip: 0, Basic: 0 };
        response.data.forEach((item) => {
          newData[item.roomType] = (newData[item.roomType] || 0) + item.count;
        });
        setRoomData(newData);
      })
      .catch((error) => console.error('Error fetching data:', error));
  }, []);

  const data = {
    labels: ['Luxury', 'Vip', 'Basic'],
    datasets: [
      {
        label: 'Số lượng đặt phòng:',
        data: [roomData.Luxury, roomData.Vip, roomData.Basic],
        backgroundColor: [
          'rgb(255, 99, 132)',
          'rgb(54, 162, 235)',
          'rgb(255, 205, 86)',
        ],
        hoverOffset: 4,
      },
    ],
  };

  const options = {
    plugins: {
      legend: {
        display: true,
        position: 'right', 
        labels: {
          boxWidth: 20,
          padding: 15,
          font: {
            size: 14, 
          },
        },
      },
      tooltip: {
        callbacks: {
          label: (context) => `${context.raw} phòng`,
        },
      },
    },
    maintainAspectRatio: false, 
  };

  return (
    <div
      style={{
        width: '500px',
        height: '500px',
        position: 'relative',
        margin: '0 auto',
        backgroundColor: '#fff',
        padding: '10px',
        borderRadius: '10px',
        boxShadow: '0px 4px 6px rgba(0, 0, 0, 0.1)',
      }}
    >

      <Pie data={data} options={options} />
    </div>
  );
};

export default BaoCaoP;
