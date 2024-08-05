USE [master]
create database NTTTestLP;
go

USE [NTTTestLP]
GO
/****** Object:  Table [dbo].[cliente]    Script Date: 5/8/2024 1:29:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cliente](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[contrasenia] [varchar](255) NULL,
	[estado] [bit] NULL,
	[persona_persona_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[cuenta]    Script Date: 5/8/2024 1:29:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[cuenta](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[estado] [bit] NULL,
	[numero_cuenta] [varchar](255) NULL,
	[saldo_inicial] [float] NOT NULL,
	[tipo_cuenta] [varchar](255) NULL,
	[cliente_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[movimiento]    Script Date: 5/8/2024 1:29:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[movimiento](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[fecha] [datetime2](6) NULL,
	[saldo] [float] NOT NULL,
	[tipo_movimiento] [varchar](255) NULL,
	[valor] [float] NOT NULL,
	[cuenta_id] [bigint] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[persona]    Script Date: 5/8/2024 1:29:23 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[persona](
	[persona_id] [bigint] IDENTITY(1,1) NOT NULL,
	[direccion] [varchar](255) NULL,
	[edad] [int] NOT NULL,
	[genero] [varchar](255) NULL,
	[identificacion] [varchar](255) NULL,
	[nombre] [varchar](255) NULL,
	[telefono] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[persona_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[cliente]  WITH CHECK ADD  CONSTRAINT [FKsx672v0wwantxy6x4tguuwbw7] FOREIGN KEY([persona_persona_id])
REFERENCES [dbo].[persona] ([persona_id])
GO
ALTER TABLE [dbo].[cliente] CHECK CONSTRAINT [FKsx672v0wwantxy6x4tguuwbw7]
GO
ALTER TABLE [dbo].[cuenta]  WITH CHECK ADD  CONSTRAINT [FK4p224uogyy5hmxvn8fwa2jlug] FOREIGN KEY([cliente_id])
REFERENCES [dbo].[cliente] ([id])
GO
ALTER TABLE [dbo].[cuenta] CHECK CONSTRAINT [FK4p224uogyy5hmxvn8fwa2jlug]
GO
ALTER TABLE [dbo].[movimiento]  WITH CHECK ADD  CONSTRAINT [FK4ea11fe7p3xa1kwwmdgi9f2fi] FOREIGN KEY([cuenta_id])
REFERENCES [dbo].[cuenta] ([id])
GO
ALTER TABLE [dbo].[movimiento] CHECK CONSTRAINT [FK4ea11fe7p3xa1kwwmdgi9f2fi]
GO
