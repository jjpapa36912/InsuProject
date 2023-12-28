import React, {useEffect, useState} from 'react';
import axios from "axios";
import {Button, ButtonGroup} from "react-bootstrap";
import {MEMBER_ENTITY_USER_ID_SESSION} from "../layout/Header";
import {useNavigate} from "react-router-dom";

const BoardRegisterOrderView = () => {
  const [orderTitle, setOrderTitle] = useState();
  const [orderContents, setOrderContents] = useState();
  const orderUser = sessionStorage.getItem(MEMBER_ENTITY_USER_ID_SESSION)
  const navigate = useNavigate()



  const handleWriteCompleteClick = async () => {
    await axios.post("/registerOrder", {
      orderUser,
      orderTitle,
      orderContents
    }).then(response => {
      if (response.status == 200) {
        console.log('Order successful');
        navigate("/boardList");
      }
    }).catch(error => {
      console.log('Failed to save order data!', error)
    });
  }

  return (
      <div>
        <h2>Registration View</h2>
        <div>
          <p>User ID: </p><textarea
            value={orderUser}
            rows={1} // Specify the number of visible rows
            cols={10} // Specify the number of visible columns
        />
          <div><p>Title: </p><textarea
              value={orderTitle}
              onChange={(e) => setOrderTitle(e.target.value)}
              rows={1} // Specify the number of visible rows
              cols={50} // Specify the number of visible columns
          />
          </div>
          <p>Description: </p><textarea
            value={orderContents}
            onChange={(e) => setOrderContents(e.target.value)}
            rows={10} // Specify the number of visible rows
            cols={50} // Specify the number of visible columns
        />
          <hr/>
          <ButtonGroup>
            <Button size="sm" color="primary"
                    onClick={() => handleWriteCompleteClick()}>등록</Button>
          </ButtonGroup>
        </div>

      </div>
  );
};

export default BoardRegisterOrderView;