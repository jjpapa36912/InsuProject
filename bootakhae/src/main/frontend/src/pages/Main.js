import React from 'react';
import {useNavigate} from "react-router-dom";

const Main = () => {
  const navigate = useNavigate();
  navigate('/board');
  return null;
}

export default Main;