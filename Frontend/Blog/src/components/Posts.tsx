import { useEffect, useState } from "react";
import { posts$ } from "../services/api.service";
import { BASE_URL } from "../services/helper";
import CommentComponent from "./Comments";

type PostType = {
  postId: string;
  title: string;
  content: string;
  addedDate: string;
  imageName: string;  
  category: {
    categoryId: string;
    categoryTitle: string;
  };
  user: {
    userId: string;
    name: string;
    role: string;
    about: string;
  };
  comments: {
    id: string;
    content: string;
  }[];
};

export default function Post() {
  const [posts, setPosts] = useState<PostType[]>([]);

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await posts$();
        console.log("🚀 ~ response:", response.data.content);
        setPosts(response.data.content);
      } catch (error) {
        console.error("Error fetching posts:", error);
      }
    };
    fetchPosts();
  }, []);

  return (
    <div className="bg-white py-4">
      <div className="mx-auto max-w-7xl px-6 lg:px-8">
        <div className="mx-auto max-w-2xl lg:mx-0">
          <h2 className="text-4xl font-semibold tracking-tight text-gray-900 sm:text-5xl">From Our Blog</h2>
          <p className="mt-2 text-lg text-gray-600">Recent Blog Posts</p>
        </div>
        <div className="mx-auto mt-10 grid max-w-2xl grid-cols-1 gap-x-8 gap-y-16 border-t border-gray-200 pt-10 sm:mt-16 sm:pt-16 lg:mx-0 lg:max-w-none lg:grid-cols-3">
          {posts.map((post) => (
            <article key={post.postId} className="flex max-w-xl flex-col items-start justify-between border border-gray-200 p-4 rounded-lg shadow-sm">
              <div className="flex items-center gap-x-4 text-xs">
                <time dateTime={post.addedDate} className="text-gray-500">
                  {new Date(post.addedDate).toDateString()}
                </time>
                <a href="#" className="relative z-10 rounded-full bg-gray-50 px-3 py-1.5 font-medium text-gray-600 hover:bg-gray-100">
                  {post.category.categoryTitle}
                </a>
              </div>
              <div className="group relative">
                <h3 className="mt-3 text-lg font-semibold text-gray-900 group-hover:text-gray-600">
                  <a href="#">
                    <span className="absolute inset-0" />
                    {post.title}
                  </a>
                </h3>
                <p className="mt-5 line-clamp-2 text-sm text-gray-600">{post.content}</p>
              </div>
              <div className="relative mt-8 flex items-center gap-x-4">

                <img alt="Author" src={BASE_URL +'/api/post/image/'+ post.imageName} className="size-10 rounded-full bg-gray-50" />
                <div className="text-sm">
                  <p className="font-semibold text-gray-900">
                    <a href="#">
                      <span className="absolute inset-0" />
                      {post.user.name}
                    </a>
                  </p>
                  <p className="text-gray-600">{post.user.role}</p>
                </div>
              </div>
              <CommentComponent comments={post.comments} />
              </article>
          ))}
        </div>
      </div>
    </div>
  );
}
