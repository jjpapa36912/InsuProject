import React from 'react';
import {useParams} from "react-router-dom";

const Main = ({ loginId, onLogout }) => {
  const { data } = useParams();
  return (
      <div>
        <h2>Welcome, {data}!</h2>
        <button onClick={onLogout}>Logout</button>
      </div>
  );
};

export default Main;