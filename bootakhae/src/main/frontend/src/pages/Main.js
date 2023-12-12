import React from 'react';
import {useNavigate, useParams} from "react-router-dom";
import axios from "axios";

const Main = () => {
  const {data} = useParams();
  const navigate = useNavigate();

  const onLogout = async () => {
    try {
      const response = await axios.get('/logout');
      if (response.status === 200) {
        console.log('Login successful');
        navigate('/login');
      }
    } catch (error) {
      console.error('Login failed', error);

    }

    // navigate('/logout');
  }

  return (
      <div>
        <h2>Welcome, {data}!</h2>
        <button onClick={onLogout}>Logout</button>
      </div>
  );
};

export default Main;