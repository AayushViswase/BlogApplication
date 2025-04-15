import React, { useState } from "react";
import { CommentComponentProps } from "./Comments";

const CommentsListComponent: React.FC<CommentComponentProps> = ({ comments, noCommentsToDisplay = 2 }) => {
  const [expandedId, setExpandedId] = useState<string | null>(null);

  const toggleExpand = (id: string) => {
    setExpandedId((prev) => (prev === id ? null : id));
  };

  return (
    <ul className="p-0 w-full">
      {comments.slice(0, noCommentsToDisplay).map((comment) => {
        const isExpanded = expandedId === comment.id;

        return (
          <React.Fragment key={comment.id}>
            <li className="flex items-start space-x-3 py-2 cursor-pointer hover:bg-gray-50 transition-colors" onClick={() => toggleExpand(comment.id)}>
              <div className="flex-shrink-0 w-7 h-7 rounded-full bg-gray-300"></div>
              <p className={`text-sm text-gray-800 ${isExpanded ? "" : "truncate"}`}>{comment.content}</p>
            </li>
            <li className="ml-10 border-t border-gray-200" />
          </React.Fragment>
        );
      })}
    </ul>
  );
};

export default CommentsListComponent;
