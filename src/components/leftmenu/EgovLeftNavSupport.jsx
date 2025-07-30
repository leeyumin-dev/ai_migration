import { NavLink } from "react-router-dom";
import URL from "@/constants/url";

function EgovLeftNavSupport() {
  return (
    <div className="nav">
      <div className="inner">
        <h2>고객지원</h2>
        <ul className="menu4">
          <li>
            <NavLink
              to={URL.SUPPORT_TRANSFORM}
              className={({ isActive }) => (isActive ? "cur" : "")}
            >
              변환
            </NavLink>
          </li>
          <li>
            <NavLink
              to={URL.SUPPORT_SECURITY}
              className={({ isActive }) => (isActive ? "cur" : "")}
            >
              보안
            </NavLink>
          </li>
          <li>
            <NavLink
              to={URL.SUPPORT_GUIDE}
              className={({ isActive }) => (isActive ? "cur" : "")}
            >
              가이드
            </NavLink>
          </li>
        </ul>
      </div>
    </div>
  );
}

export default EgovLeftNavSupport;
