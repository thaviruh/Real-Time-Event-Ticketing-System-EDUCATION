import React, { useState } from "react";

function Form({
  totalTickets,
  maxTickets,
  count,
  count2,
  setTotalTickets,
  setMaxTickets,
  setCount,
  setCount2,
  handleConfigure,
}) {
  const [errorMessage, setErrorMessage] = useState(""); // State to track error messages

  // Enhanced handleConfigure function to check for empty or invalid fields
  const enhancedHandleConfigure = () => {
    // Check if any fields are empty or have invalid values (zero or negative)
    if (!totalTickets || totalTickets <= 0 ||
        !maxTickets || maxTickets <= 0 ||
        !count || count <= 0 ||
        !count2 || count2 <= 0) {
      setErrorMessage("All fields must be filled with numbers greater than zero.");
      return; // Stop the configuration process if any field is invalid
    }
    setErrorMessage(""); // Clear any error messages if all fields are valid
    handleConfigure(); // Call the original handleConfigure function
  };

  // Helper function to ensure only non-negative numbers are allowed
  const handleNonNegativeChange = (setter) => (e) => {
    const value = e.target.value;
    if (value === "" || Number(value) >= 0) {
      setter(value);
    }
  };

  return (
    <div>
      {errorMessage && <div className="text-red-500 mb-2">{errorMessage}</div>}
      <div className="mb-4">
        <label className="block font-medium">Total Tickets:</label>
        <input
          type="number"
          value={totalTickets}
          onChange={handleNonNegativeChange(setTotalTickets)}
          className="border border-gray-300 rounded p-2 w-full text-lg"
        />
      </div>
      <div className="mb-4">
        <label className="block font-medium">Ticket Release Rate:</label>
        <input
          type="number"
          value={maxTickets}
          onChange={handleNonNegativeChange(setMaxTickets)}
          className="border border-gray-300 rounded p-2 w-full text-lg"
        />
      </div>
      <div className="mb-4">
        <label className="block font-medium">Ticket Retrieval Rate:</label>
        <input
          type="number"
          value={count}
          onChange={handleNonNegativeChange(setCount)}
          className="border border-gray-300 rounded p-2 w-full text-lg"
        />
      </div>
      <div className="mb-4">
        <label className="block font-medium">Maximum Tickets:</label>
        <input
          type="number"
          value={count2}
          onChange={handleNonNegativeChange(setCount2)}
          className="border border-gray-300 rounded p-2 w-full text-lg"
        />
      </div>
      <div className="flex justify-center">
        <button
          onClick={enhancedHandleConfigure}
          className="bg-blue-500 text-white px-5 py-3 rounded"
        >
          Configure
        </button>
      </div>
    </div>
  );
}

export default Form;
