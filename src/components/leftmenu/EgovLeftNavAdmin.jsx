import { NavLink } from "react-router-dom";
import URL from "@/constants/url";

function EgovLeftNavAdmin() {
  return (
    <div className="nav">
      <div className="inner">
        <h2>사이트관리</h2>
        <ul className="menu4">
          <li>
            <NavLink
              to={URL.ADMIN_NOTICE}
              className={({ isActive }) => (isActive ? "cur" : "")}
            >
              공지사항관리
            </NavLink>
          </li>
          <li>
            <NavLink
              to={URL.ADMIN_FAQ}
              className={({ isActive }) => (isActive ? "cur" : "")}
            >
              FAQ관리
            </NavLink>
          </li>
          <li>
            <NavLink
              to={URL.ADMIN_QNA}
              className={({ isActive }) => (isActive ? "cur" : "")}
            >
              Q&A관리
            </NavLink>
          </li>
          <li>
            <NavLink
              to={URL.ADMIN_MEMBERS}
              className={({ isActive }) => (isActive ? "cur" : "")}
            >
              회원관리
            </NavLink>
          </li>
          <li>
            <NavLink
              to={URL.ADMIN_MANAGER}
              className={({ isActive }) => (isActive ? "cur" : "")}
            >
              관리자관리
            </NavLink>
          </li>
        </ul>
      </div>
    </div>
  );
}

export default EgovLeftNavAdmin;
