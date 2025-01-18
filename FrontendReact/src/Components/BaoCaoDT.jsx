import React, { useEffect, useState } from 'react';
import { Line } from '@ant-design/plots';
import axios from 'axios';
import { Button } from 'antd'; 

const BaoCaoDT = () => {
  const [data, setData] = useState([]);
  const [currentMonth, setCurrentMonth] = useState('2024-12');
  const [allData, setAllData] = useState([]); 

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get('/home/hoadon/doanhthu');
        const result = response.data;
        const groupedData = groupByMonth(result);
        setAllData(groupedData);
        filterDataForMonth(currentMonth, groupedData);
      } catch (error) {
        console.error('Không lấy được dữ liệu', error);
      }
    };

    fetchData();
  }, [currentMonth]);
  const groupByMonth = (data) => {
    const result = [];
    const months = [...new Set(data.map(item => `${item.year}-${item.month}`))];
    months.forEach(month => {
      const monthData = data.filter(item => `${item.year}-${item.month}` === month);
      const allDaysInMonth = getAllDaysInMonth(month);
      const fullMonthData = allDaysInMonth.map(day => {
        const dataForDay = monthData.find(item => item.day === day);
        return {
          year: month.split('-')[0],
          month: month.split('-')[1],
          day: day,
          revenue: dataForDay ? dataForDay.revenue : 0,
        };
      });
      result.push(...fullMonthData);
    });

    return result;
  };

  // Hàm tạo tất cả các ngày trong một tháng (bao gồm ngày không có doanh thu)
  const getAllDaysInMonth = (month) => {
    const [year, monthNum] = month.split('-');
    const daysInMonth = new Date(year, monthNum, 0).getDate();
    return Array.from({ length: daysInMonth }, (_, i) => i + 1);
  };

  const formatNumberWithDots = (value) => {
    return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.');
  };

  // Hàm lọc dữ liệu cho tháng hiện tại
  const filterDataForMonth = (month, groupedData) => {
    const filteredData = groupedData.filter(item => `${item.year}-${item.month}` === month);
    setData(filteredData);
  };

  // Hàm chuyển đổi tháng
  const changeMonth = (direction) => {
    const [year, month] = currentMonth.split('-');
    let newMonth = parseInt(month) + direction;
    let newYear = parseInt(year);

    // Điều chỉnh nếu tháng vượt qua tháng 12 hoặc tháng 1
    if (newMonth > 12) {
      newMonth = 1;
      newYear += 1;
    } else if (newMonth < 1) {
      newMonth = 12;
      newYear -= 1;
    }

    const newMonthStr = `${newYear}-${newMonth.toString().padStart(2, '0')}`;
    setCurrentMonth(newMonthStr); 
    filterDataForMonth(newMonthStr, allData);
  };

  const config = {
    data,
    xField: 'day',
    yField: 'revenue',
    seriesField: 'month',
    xAxis: {
      title: {
        text: 'Ngày',
      },
      label: {
        style: {
          fontSize: 12, 
          fill: '#FF6347', 
          fontWeight: 'bold', 
        },
      },
      tickCount: 15, 
      labelOffset: 12, 
    },
    yAxis: {
      title: {
        text: 'Doanh Thu (VND)',
      },
      label: {
        formatter: (value) => `${formatNumberWithDots(value)} VND`, 
        style: {
          fontSize: 12, 
          fill: '#228B22', 
          fontWeight: 'bold', 
        },
      },
      labelOffset: 12, 
    },
    lineStyle: {
      lineWidth: 3,
      stroke: '#1E90FF',
    },
    point: {
      size: 6,
      shape: 'circle',
      color: '#FF6347', 
    },
    legend: {
      position: 'top-right',
      title: {
        style: {
          fontSize: 12,
          fill: '#000000',
        },
      },
    },
    meta: {
      revenue: {
        alias: 'Doanh Thu',
        formatter: (value) => `${formatNumberWithDots(value)} VND`,
      },
      day: {
        alias: 'Ngày',
      },
    },
  };

  return (
    <div>
      <h2>Báo Cáo Doanh Thu Tháng {currentMonth}</h2> 
      
      <Button
        icon="<"
        onClick={() => changeMonth(-1)}
        style={{ marginRight: 10 }}
      />
      <Button
        icon=">"
        onClick={() => changeMonth(1)}
      />

      <Line {...config} />
    </div>
  );
};

export default BaoCaoDT;
