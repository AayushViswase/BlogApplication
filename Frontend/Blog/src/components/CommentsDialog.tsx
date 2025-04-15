import Button from "@mui/material/Button";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";
import * as React from "react";
import CommentsListComponent from "./CommentList";
import { CommentComponentProps } from "./Comments";

interface CommentsDialogComponentProps extends CommentComponentProps {
  open: boolean;
  onClose: () => void;
}

const CommentsDialogComponent: React.FC<CommentsDialogComponentProps> = ({ comments, open, onClose }) => {
  console.log("🚀 ~ comments:", comments);
  const scomments = [
    {
      id: "1",
      content: "Great article! I never realized how AI could impact education in such a profound way.",
    },
    {
      id: "2",
      content: "I think the biggest challenge will be ensuring data privacy while using AI-driven tools in classrooms.",
    },
    {
      id: "3",
      content: "Amazing insights! I'm curious to see how AI will shape personalized learning experiences.",
    },
    {
      id: "4",
      content: "As an educator, I'm excited to see more AI applications that can support both students and teachers.",
    },
    {
      id: "5",
      content: "It would be great to see AI helping students with special needs by providing customized support.",
    },
    {
      id: "6",
      content: "Great article! I never realized how AI could impact education in such a profound way.",
    },
    {
      id: "7",
      content: "I think the biggest challenge will be ensuring data privacy while using AI-driven tools in classrooms.",
    },
    {
      id: "3",
      content: "Amazing insights! I'm curious to see how AI will shape personalized learning experiences.",
    },
    {
      id: "4",
      content: "As an educator, I'm excited to see more AI applications that can support both students and teachers.",
    },
    {
      id: "5",
      content: "It would be great to see AI helping students with special needs by providing customized support.",
    },
    {
      id: "1",
      content: "Great article! I never realized how AI could impact education in such a profound way.",
    },
    {
      id: "2",
      content: "I think the biggest challenge will be ensuring data privacy while using AI-driven tools in classrooms.",
    },
    {
      id: "3",
      content: "Amazing insights! I'm curious to see how AI will shape personalized learning experiences.",
    },
    {
      id: "4",
      content: "As an educator, I'm excited to see more AI applications that can support both students and teachers.",
    },
    {
      id: "5",
      content: "It would be great to see AI helping students with special needs by providing customized support.",
    },
  ];
  const descriptionElementRef = React.useRef<HTMLElement>(null);

  React.useEffect(() => {
    if (open) {
      const { current: descriptionElement } = descriptionElementRef;
      if (descriptionElement !== null) {
        descriptionElement.focus();
      }
    }
  }, [open]);

  return (
    <Dialog open={open} onClose={onClose} scroll="paper" >
      <DialogTitle id="scroll-dialog-title">Comments</DialogTitle>
      <DialogContent dividers>
          <CommentsListComponent comments={scomments} noCommentsToDisplay={scomments.length} />
      </DialogContent>
      <DialogActions>
        <Button onClick={onClose}>Close</Button>
      </DialogActions>
    </Dialog>
  );
};

export default CommentsDialogComponent;
