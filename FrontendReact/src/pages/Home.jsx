import React from "react";
import "../Style/Home.css";

const Home = () => {
  return (
    <div className="home">
      <div className="banner-container">
        <img src="/anhNenHome.jpg" alt="Home banner" className="home-banner" />
        <h1 className="banner-title">HOTEL</h1>
        <h2 className="banner-subTitle">MANAGEMENT</h2>
      </div>
    </div>
  );
};

export default Home;
