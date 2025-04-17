import React, { useState } from "react";
import { CommentComponentProps } from "./Comments";
import CommentsDialogComponent from "./CommentsDialog";

const CommentsListComponent: React.FC<CommentComponentProps> = ({ comments, noCommentsToDisplay = 2 }) => {
  const [dialogOpen, setDialogOpen] = useState(false);
  const [expandedId, setExpandedId] = useState<string | null>(null);

  const handleDialogOpen = () => setDialogOpen(true);
  const handleDialogClose = () => setDialogOpen(false);

  const toggleExpand = (id: string) => {
    setExpandedId((prev) => (prev === id ? null : id));
  };

  const visibleComments = comments.slice(0, noCommentsToDisplay);
  const shouldShowMore = comments.length > noCommentsToDisplay;

  return (
    <div>
      <ul className="p-0 w-full">
        {visibleComments.map((comment) => {
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

      {shouldShowMore && (
        <div className="flex justify-end items-center gap-x-2">
          <button onClick={handleDialogOpen} className="text-blue-600 text-sm hover:underline pd-0">
            More...
          </button>
        </div>
      )}

      {dialogOpen && <CommentsDialogComponent open={dialogOpen} onClose={handleDialogClose} comments={comments} noCommentsToDisplay={comments.length} />}
    </div>
  );
};

export default CommentsListComponent;
