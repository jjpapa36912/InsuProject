import React, {useEffect, useState} from 'react';
import {useNavigate, useParams} from "react-router-dom";
import axios from "axios";

const BoardDetailView = () => {
  const id = useParams();
  const [orderEntity, setOrderEntity] = useState();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.post('/boardItem/'+id.id);
        console.log("!!!!!!!!!!!!!!"+response.data.userId);
        setOrderEntity(response.data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, [id]);

  return (
      <div>
        <h2>Detail View</h2>
        {orderEntity && ( // Check if item is truthy before rendering
            <div key={orderEntity.id}>
              <p>Title: {orderEntity.orderTitle}</p>
              <p>Description: {orderEntity.orderContents}</p>
            </div>
        )}
      </div>
  );
};

export default BoardDetailView;