import React from 'react';
import {useNavigate} from "react-router-dom";

const Home = () => {
  const navigate = useNavigate();
  navigate('/home');
  return null;
}

export default Home;